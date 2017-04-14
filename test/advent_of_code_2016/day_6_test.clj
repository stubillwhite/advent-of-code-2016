(ns advent-of-code-2016.day-6-test
  (:require [advent-of-code-2016.day-6 :refer :all]
            [clojure.test :refer :all]
            [clojure.string :as string]))

(def test-messages
  ["eedadn"
   "drvtee"
   "eandsr"
   "raavrd"
   "atevrs"
   "tsrnev"
   "sdttsa"
   "rasrtv"
   "nssdts"
   "ntnada"
   "svetve"
   "tesnvt"
   "vntsnd"
   "vrdear"
   "dvrsen"
   "enarar"])

(deftest error-correct-given-example-input-then-example-output
  (is (= (error-correct test-messages) "easter")))

(deftest modified-error-correct-given-example-input-then-example-output
  (is (= (modified-error-correct test-messages) "advent")))
