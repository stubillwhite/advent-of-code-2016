(ns advent-of-code-2016.day-7
  (:require [advent-of-code-2016.utils :refer [def-]]
            [clojure.java.io :as io]
            [clojure.string :as string])
  (:import java.security.MessageDigest
           javax.xml.bind.annotation.adapters.HexBinaryAdapter))

(def addresses
  (string/split (slurp (io/resource "day-7-input.txt")) #"\n"))

(def- re-abba "(\\w)(?!\\1)(\\w)\\2\\1")

(def- re-hypernet
  (str
   "\\[\\w*?"
   re-abba
   "\\w*?\\]"))

(defn- abba-anywhere? [s]
  (not (nil? (re-find (re-pattern re-abba) s))))

(defn- abba-in-hypernet? [s]
  (not (nil? (re-find (re-pattern re-hypernet) s))))

(defn supports-tls? [s]
  (and (abba-anywhere? s) (not (abba-in-hypernet? s))))

(defn solution-part-one []
  (->> addresses
       (map supports-tls?)
       (filter identity)
       count))

