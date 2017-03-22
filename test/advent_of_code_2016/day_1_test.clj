(ns advent-of-code-2016.day-1-test
  (:require [advent-of-code-2016.day-1 :refer :all]
            [clojure.test :refer :all]))

(deftest distance-to-destination-one-given-example-test-case-then-expected-result []
  (is (= (distance-to-destination (create-state ["R2" "L3"])           all-steps-completed?) 5))
  (is (= (distance-to-destination (create-state ["R2" "R2" "R2"])      all-steps-completed?) 2))
  (is (= (distance-to-destination (create-state ["R5" "L5" "R5" "R3"]) all-steps-completed?) 12)))

(deftest solution-part-one-then-expected-result []
  (is (= (solution-part-one) 242)))

(deftest distance-to-destination-two-given-example-test-case-then-expected-result []
  (is (= (distance-to-destination (create-state ["R8" "R4" "R4" "R8"]) visited-twice?) 4)))

(deftest solution-part-two-then-expected-result []
  (is (= (solution-part-two) 150)))
