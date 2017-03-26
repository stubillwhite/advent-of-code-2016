(ns advent-of-code-2016.day-4-test
  (:require [advent-of-code-2016.day-4 :refer :all]
            [clojure.test :refer :all]
            [clojure.string :as string]))

(def real-room-1 "aaaaa-bbb-z-y-x-123[abxyz]")
(def real-room-2 "a-b-c-d-e-f-g-h-987[abcde]")
(def real-room-3 "not-a-real-room-404[oarel]")
(def decoy-room-1 "totally-real-room-200[decoy]")

(def example-input
  [real-room-1
   real-room-2
   real-room-3
   decoy-room-1])

(deftest decode-room-id-given-example-input-then-destructures
  (is (= (decode-room-id real-room-1) {:name "aaaaa-bbb-z-y-x" :security-id 123 :checksum "abxyz"})))

(deftest real?-given-real-room-then-true
  (is (= (real? (decode-room-id real-room-1)) true))
  (is (= (real? (decode-room-id real-room-2)) true))
  (is (= (real? (decode-room-id real-room-3)) true)))

(deftest real?-given-decoy-room-then-false
  (is (= (real? (decode-room-id decoy-room-1)) false)))

(deftest sum-of-sector-ids-given-example-input-then-expected-result
  (is (= (sum-of-sector-ids example-input) 1514)))

(deftest solution-part-one-then-expected-result
  (is (= (solution-part-one) 245102)))

(deftest decrypt-given-exaple-input-then-expected-result
  (is (= (decrypt "qzmt-zixmtkozy-ivhz" 343) "very encrypted name")))

(deftest solution-part-two-then-expected-result
  (is (= (solution-part-two) 324)))
