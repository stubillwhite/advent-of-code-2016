(ns advent-of-code-2016.day-8
  (:require [advent-of-code-2016.utils :refer [def-]]
            [clojure.string :as string]
            [clojure.java.io :as io]))

;; Rules

;; - rect AxB turns on all of the pixels in a rectangle at the top-left of the screen which is A wide and B tall.
;; 
;; - rotate row y=A by B shifts all of the pixels in row A (0 is the top row) right by B pixels. Pixels that would fall
;;   off the right end appear at the left end of the row.
;;
;; - rotate column x=A by B shifts all of the pixels in column A (0 is the left column) down by B pixels. Pixels that
;;   would fall off the bottom appear at the top of the column.

(def pixel-on  \X)
(def pixel-off \space)

;; There are probably better data structures than this, but for ease of implementation a simple 2D array will do. We can
;; handle column rotation by transposing the array, rotating the row, then transposing back. Lazy and inefficient but
;; simple to implement.

(defn display [width height]
  (for [y (range height)]
    (repeat width pixel-off)))

(defn- rect [disp width height]
  (let [rect-row (repeat width pixel-on)]
    (map-indexed
     (fn [idx row]
       (if (< idx height)
         (concat rect-row (drop width row))
         row))
     disp)))

(defn- transpose [disp]
  (loop [trans []
         disp  disp]
    (if (empty? (first disp))
      trans
      (recur (conj trans (map first disp))
             (map rest disp)))))

(defn- rotate-left [s n]
  (->> s (cycle) (drop n) (take (count s))))

(defn- rotate-right [s n]
  (rotate-left s (- (count s) n)))

(defn- rotate-row [disp n steps]
  (concat
   (take n disp)
   (list (rotate-right (nth disp n) steps))
   (drop (inc n) disp)))

(defn- rotate-column [disp n steps]
  (-> disp
      (transpose)
      (rotate-row n steps)
      (transpose)))

;; Command handlers

(def- command-regexes
  {#"rect (\d+)x(\d+)"               rect
   #"rotate row y=(\d+) by (\d+)"    rotate-row
   #"rotate column x=(\d+) by (\d+)" rotate-column})

(defn- parse-command [cmd]
  (some identity
        (for [[r f] command-regexes]
          (if-let [[match & args] (first (re-seq r cmd))]
            [f args]))))

(defn execute-command [disp cmd]
  (if-let [[f args] (parse-command cmd)]
    (apply (partial f disp) (map #(Long/parseLong %) args))
    (throw (RuntimeException. (format "Unrecognised command '%s'" cmd)))))

(defn display-to-string [disp]
  (->> disp
       (map string/join)
       (string/join \newline)))

;; Solution

(defn- pixels-lit [disp]
  (transduce
   (map (fn [x] (if (= pixel-on x) 1 0)))
   +
   0
   (apply concat disp)))

(def commands
  (string/split (slurp (io/resource "day-8-input.txt")) #"\n"))

(defn solution-part-one []
  (pixels-lit
   (reduce execute-command
           (display 50 6)
           commands)))

;; One of the rare cases where my debugging code has actually done all the work for part two already, so this is trivial

(defn solution-part-two []
  (print
   (display-to-string
    (reduce execute-command
            (display 50 6)
            commands))))
