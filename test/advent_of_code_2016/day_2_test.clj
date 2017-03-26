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

(deftest square-terminal-then-expected-terminal
  (let [expected {:start-pos [1 1]
                  :keypad    {[0 0] \1 [1 0] \2 [2 0] \3
                              [0 1] \4 [1 1] \5 [2 1] \6
                              [0 2] \7 [1 2] \8 [2 2] \9}}]
    (is (= square-terminal expected))))

(deftest calculate-code-given-example-input-then-example-output
  (is (= (calculate-code (initial-state square-terminal) example-instructions) "1985")))

(deftest diamond-keypad-then-keypad
  (let [expected {:start-pos [0 3]
                  :keypad    {                  [2 0] \1
                                       [1 1] \2 [2 1] \3 [3 1] \4
                              [0 2] \5 [1 2] \6 [2 2] \7 [3 2] \8 [4 2] \9
                                       [1 3] \A [2 3] \B [3 3] \C
                                                [2 4] \D }}]
    (is (= diamond-terminal expected))))

(deftest solution-part-one-then-expected-result
  (is (= (solution-part-one) "76792")))

(deftest tcalculate-code-given-example-input-then-example-output
  (is (= (calculate-code (initial-state diamond-terminal) example-instructions) "5DB3")))

(deftest solution-part-two-then-expected-result
  (is (= (solution-part-two) "A7AC3")))
