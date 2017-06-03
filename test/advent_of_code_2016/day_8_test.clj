(ns advent-of-code-2016.day-8-test
  (:require [advent-of-code-2016
             [day-8 :refer :all]
             [utils :refer [def-]]]
            [clojure
             [string :as string]
             [test :refer :all]])
  (:import java.util.regex.Pattern))

(deftest print-display-given-blank-display-then-blank
  (let [expected (string/join \newline ["    "
                                        "    "
                                        "    "])]
    (is (= expected (-> (display 4 3)
                        (display-to-string))))))

(deftest rect-then-draws-rectangle
  (let [expected (string/join \newline ["XX  "
                                        "XX  "
                                        "XX  "
                                        "    "])]
    (is (= expected (-> (display 4 4)
                        (execute-command "rect 2x3")
                        (display-to-string))))))

(deftest rotate-row-then-rotates-row-with-wrap
  (let [expected (string/join \newline ["XX  "
                                        "X  X"
                                        "XX  "
                                        "    "])]
    (is (= expected (-> (display 4 4)
                        (execute-command "rect 2x3")
                        (execute-command "rotate row 1 3")
                        (display-to-string))))))

(deftest rotate-column-then-rotates-row-with-wrap
  (let [expected (string/join \newline ["XX  "
                                        "XX  "
                                        "X   "
                                        " X  "])]
    (is (= expected (-> (display 4 4)
                        (execute-command "rect 2x3")
                        (execute-command "rotate column 1 3")
                        (display-to-string))))))

(deftest example-input-then-example-output
  (let [expected (string/join \newline [" X  X X"
                                        "X X    "
                                        " X     "])]
    (is (= expected (-> (display 7 3)
                        (execute-command "rect 3x2")
                        (execute-command "rotate column 1 1")
                        (execute-command "rotate row 0 4")
                        (execute-command "rotate column 1 1")
                        (display-to-string))))))
