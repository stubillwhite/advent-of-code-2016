(ns advent-of-code-2016.day-9-test
  (:require [advent-of-code-2016
             [day-9 :refer :all]
             [utils :refer [def-]]]
            [clojure
             [string :as string]
             [test :refer :all]])
  (:import java.util.regex.Pattern))

;; ADVENT contains no markers and decompresses to itself with no changes, resulting in a decompressed length of 6.
;; A(1x5)BC repeats only the B a total of 5 times, becoming ABBBBBC for a decompressed length of 7.
;; (3x3)XYZ becomes XYZXYZXYZ for a decompressed length of 9.
;; A(2x2)BCD(2x2)EFG doubles the BC and EF, becoming ABCBCDEFEFG for a decompressed length of 11.
;; (6x1)(1x3)A simply becomes (1x3)A - the (1x3) looks like a marker, but because it's within a data section of another marker, it is not treated any differently from the A that comes after it. It has a decompressed length of 6.
;; X(8x2)(3x3)ABCY becomes X(3x3)ABC(3x3)ABCY (for a decompressed length of 18), because the decompressed data from the (8x2) marker (the (3x3)ABC) is skipped and not processed further.

(deftest decompress-given-example-input-then-example-output
  (is (= "ADVENT"             (decompress "ADVENT")))
  (is (= "ABBBBBC"            (decompress "A(1x5)BC")))
  (is (= "XYZXYZXYZ"          (decompress "(3x3)XYZ")))
  (is (= "ABCBCDEFEFG"        (decompress "A(2x2)BCD(2x2)EFG")))
  (is (= "(1x3)A"             (decompress "(6x1)(1x3)A")))
  (is (= "X(3x3)ABC(3x3)ABCY" (decompress "X(8x2)(3x3)ABCY"))))
