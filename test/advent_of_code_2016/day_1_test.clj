(ns advent-of-code-2016.day-1-test
  (:require [advent-of-code-2016.day-1 :refer :all]
            [clojure.test :refer :all]))

(deftest distance-to-destination-given-example-test-case-then-expected-result []
  (is (= (distance-to-destination ["R2" "L3"]) 5))
  (is (= (distance-to-destination ["R2" "R2" "R2"]) 2))
  (is (= (distance-to-destination ["R5" "L5" "R5" "R3"]) 12)))

(deftest distance-to-destination-given-example-input-then-expected-result []
  (is (= (distance-to-destination directions-to-hq) 242)))
