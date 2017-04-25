(ns advent-of-code-2016.day-7
  (:require [advent-of-code-2016.utils :refer [def-]]
            [clojure.java.io :as io]
            [clojure.string :as string])
  (:import java.security.MessageDigest
           javax.xml.bind.annotation.adapters.HexBinaryAdapter))

(def addresses
  (string/split (slurp (io/resource "day-7-input.txt")) #"\n"))

(defn- palindrome? [s]
  (= (seq s) (reverse (seq s))))

(defn abba? [s]
  (loop [s (seq s)]
    (let [[a b c d] s]
      (cond
        (nil? d)                     false
        (and (palindrome? [a b c d])
             (not (= a b)))          true
        :else                        (recur (rest s))))))

(defn- hypernet? [x]
  (and (string/starts-with? x "[")
       (string/ends-with? x "]")) )

(defn parse-address [s]
  (let [tokens (re-seq #"[^\[\]]+|\[.*?\]" s)
        parts  (group-by hypernet? tokens)]
    {:hyper (map (fn [x] (subs x 1 (dec (.length x)))) (parts true))
     :other (parts false)}))

(defn supports-tls? [s]
  (let [{:keys [hyper other]} (parse-address s)]
    (true? (and (some abba? other)
                (not (some abba? hyper))))))

(defn solution-part-one []
  (->> addresses
       (map supports-tls?)
       (filter identity)
       count))

