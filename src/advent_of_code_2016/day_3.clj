(ns advent-of-code-2016.day-3
  (:require [clojure.java.io :as io]
            [clojure.string :as string]))

(defn- parse-line [s]
  (map #(Long/valueOf %) (string/split (string/trim s) #"\s+")))

(def candidate-triangles
  (let [raw-input (slurp (io/resource "day-3-input.txt"))]
    (mapcat parse-line (string/split raw-input #"\n"))))

(defn valid? [t]
  (let [[a b c] (sort t)]
    (> (+ a b) c)))

(def horizontal-partitioner
  (fn [s] (partition 3 s)))

(def vertical-partitioner
  (fn [s] (apply concat
                (for [[a d g
                       b e h
                       c f i] (partition 9 s)]
                  [[a b c] [d e f] [g h i]]))))

(defn count-of-valid-triangles [ts partitioner]
  (count (filter valid? (partitioner ts))))

(defn solution-part-one []
  (count-of-valid-triangles candidate-triangles horizontal-partitioner))

(defn solution-part-two []
  (count-of-valid-triangles candidate-triangles vertical-partitioner))


