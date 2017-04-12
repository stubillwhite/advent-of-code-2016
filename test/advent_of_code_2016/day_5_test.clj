(ns advent-of-code-2016.day-5-test
  (:require [advent-of-code-2016.day-5 :refer :all]
            [clojure.test :refer :all]
            [clojure.string :as string]))

(deftest simple-password-given-example-input-then-example-password
  (is (= (simple-password "abc") "18F47A30")))

(deftest complex-password-given-example-input-then-example-password
  (is (= (complex-password "abc") "05ACE8E3")))
