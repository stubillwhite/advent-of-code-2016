(ns advent-of-code-2016.day-3
  (:require [clojure.java.io :as io]
            [clojure.string :as string]))

(defn make-triangle [s]
  (map #(Long/valueOf %) (string/split (string/trim s) #"\s+")))

(def candidate-triangles
  (let [raw-input (slurp (io/resource "day-3-input.txt"))]
    (for [line (string/split raw-input #"\n")]
      (make-triangle line))))

(defn valid? [t]
  (let [[a b c] (sort t)]
    (> (+ a b) c)))

(defn count-of-valid-triangles [ts]
  (count (filter valid? ts)))

(defn solution-part-one []
  (count-of-valid-triangles candidate-triangles))

(solution-part-one)
