(ns advent-of-code-2016.day-2-test
  (:require [advent-of-code-2016
             [day-2 :refer :all]
             [utils :refer [def-]]]
            [clojure.test :refer :all]))

(def- example-instructions
  ["ULL"
   "RRDDD"
   "LURDL"
   "UUUUD"])

(deftest calculate-code-given-example-input-then-example-output []
  (is (= (calculate-code initial-state example-instructions) "1985")))
