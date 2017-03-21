(ns advent-of-code-2016.core-test
  (:require
    [clojure.test :refer :all]
    [advent-of-code-2016.core :refer :all]))

(deftest add-one-given-value-then-increments []
  (is (= 24 (add-one 23))))

