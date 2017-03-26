(ns advent-of-code-2016.day-2
  (:require [advent-of-code-2016.utils :refer [def-]]
            [clojure.java.io :as io]
            [clojure.string :as string]))

(defn create-keypad [k]
  (let [indices (for [y (range (count k))
                      x (range (count (first k)))] [x y])]
    (->> (zipmap indices (mapcat seq k))
         (filter (fn [[k v]] (not= v \space)))
         (into {}))))

(defn create-input-terminal [start-pos keypad]
  {:start-pos start-pos
   :keypad    keypad})

(def square-terminal
  (create-input-terminal
   [1 1]
   (create-keypad ["123"
                   "456"
                   "789"])))

(def diamond-terminal
  (create-input-terminal
   [0 3]
   (create-keypad ["  1  "
                   " 234 "
                   "56789"
                   " ABC "
                   "  D  "])))

(defn initial-state [input-terminal]
  {:pos    (:start-pos input-terminal)
   :digits ""
   :keypad (:keypad input-terminal)})

(defn- add-coordinates [[x1 y1] [x2 y2]]
  [(+ x1 x2) (+ y1 y2)])

(defn- delta [instruction]
  (get {\U [0 -1]
        \D [0  1]
        \L [-1 0]
        \R [1  0]}
       instruction))

(defn follow-instruction [state instr]
  (let [new-pos (add-coordinates (delta instr) (:pos state))]
    (if (contains? (:keypad state) new-pos)
      (assoc state :pos new-pos)
      state)))

(defn- enter-digit [state]
  (let [digit (get (:keypad state) (:pos state))]
    (update state :digits (fn [x] (str x digit)))))

(defn- follow-instructions-and-enter-digit [state instructions]
  (enter-digit (reduce follow-instruction state (seq instructions))))

(defn calculate-code [initial-state instructions]
  (:digits (reduce follow-instructions-and-enter-digit initial-state instructions)))

(def- keypad-instructions
  (let [raw-input (slurp (io/resource "day-2-input.txt"))]
    (string/split raw-input #"\n")))

(defn solution-part-one []
  (calculate-code (initial-state square-terminal) keypad-instructions))

(defn solution-part-two []
  (calculate-code (initial-state diamond-terminal) keypad-instructions))
