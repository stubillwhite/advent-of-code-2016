(ns advent-of-code-2016.day-7-test
  (:require [advent-of-code-2016.day-7 :refer :all]
            [clojure.test :refer :all]
            [clojure.string :as string]))

(deftest abba?-given-example-input-then-example-output
  (is (= (abba? "abba")   true))
  (is (= (abba? "abcd")   false))
  (is (= (abba? "aaaa")   false))
  (is (= (abba? "ioxxoj") true)))

(deftest supports-tls?-given-example-input-then-example-output
  (is (= (supports-tls? "abba[mnop]qrst")       true))
  (is (= (supports-tls? "abcd[bddb]xyyx")       false))
  (is (= (supports-tls? "aaaa[qwer]tyui")       false))
  (is (= (supports-tls? "ioxxoj[asdfgh]zxcvbn") true)))

(deftest supports-tls?-given-multiple-groups-then-identifies-tls
  (is (= (supports-tls? "abcd[efgh]ijkl[mnop]qrxyyxst")     true))
  (is (= (supports-tls? "abcd[efgh]ijkl[mnxyyxop]qrxyyxst") false)))
