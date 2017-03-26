(ns advent-of-code-2016.day-3-test
  (:require [advent-of-code-2016.day-3 :refer :all]
            [clojure.test :refer :all]))

(deftest valid?-given-valid-triangle-then-true
  (is (= (valid? [3 4 5]) true))
  (is (= (valid? [3 5 4]) true))
  (is (= (valid? [5 3 4]) true))
  (is (= (valid? [5 4 3]) true)))

(deftest valid?-given-invalid-triangle-then-false
  (is (= (valid? [5 10 25]) false))
  (is (= (valid? [5 25 10]) false))
  (is (= (valid? [25 5 10]) false))
  (is (= (valid? [25 10 5]) false)))

(deftest count-of-valid-triangles-given-all-invalid-then-zero
  (is (= (count-of-valid-triangles [[5 10 25]]) 0)))

(deftest count-of-valid-triangles-given-some-valid-then-count
  (is (= (count-of-valid-triangles [[3 4 5]
                                    [30 40 50]
                                    [5 10 25]]) 2)))

(deftest solution-part-one-then-expected-result
  (is (= (solution-part-one) 862)))
