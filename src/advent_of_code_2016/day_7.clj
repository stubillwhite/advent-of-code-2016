(ns advent-of-code-2016.day-7
  (:require [advent-of-code-2016.utils :refer [def-]]
            [clojure
             [set :as set]
             [string :as string]]
            [clojure.java.io :as io]))

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

;; Part two. There's probably a neater way to do this, but we just split out the super and hyper sequences, convert the
;; ABA supers to BAB so they match the hypers, and check for matches

(defn- hypernet? [x]
  (and (string/starts-with? x "[")
       (string/ends-with? x "]")) )

(def- re-aba #"(?=((\w)(?!\2)\w\2))")

(defn- parse-address [s]
  (let [tokens (re-seq #"[^\[\]]+|\[.*?\]" s)
        parts  (group-by hypernet? tokens)]
    [(->> (parts true)
          (string/join "|")
          (re-seq re-aba)
          (map second)
          (into #{})
          )
     (->> (parts false)
          (string/join "|")
          (re-seq re-aba)
          (map second)
          (into #{})
          )]))

(defn- aba-to-bab [s]
  (let [[a b a] (seq s)]
    (str b a b)))

(defn supports-ssl? [s]
  (let [[hypers supers] (parse-address s)]
    (not (empty?
          (set/intersection hypers
                            (set (map aba-to-bab supers)))))))

(defn solution-part-two []
  (->> addresses
       (filter supports-ssl?)
       (filter identity)
       count))
