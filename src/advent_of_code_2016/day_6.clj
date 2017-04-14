(ns advent-of-code-2016.day-6
  (:require [advent-of-code-2016.utils :refer [def-]]
            [clojure.java.io :as io]
            [clojure.string :as string])
  (:import java.security.MessageDigest
           javax.xml.bind.annotation.adapters.HexBinaryAdapter))

(def messages 
  (string/split (slurp (io/resource "day-6-input.txt")) #"\n"))

(defn- transpose [s]
  (apply map vector s))

(defn- freqs [s]
  (reduce
   (fn [acc x] (update acc x (fnil inc 1)))
   {}
   s))

(defn- most-frequent [fs]
  (key (apply max-key val fs)))

(defn error-correct [msgs]
  (->> (transpose msgs)
       (map freqs)
       (map most-frequent)
       (apply str)))

(defn solution-part-one []
  (error-correct messages))

(defn- least-frequent [fs]
  (key (apply min-key val fs)))

(defn modified-error-correct [msgs]
  (->> (transpose msgs)
       (map freqs)
       (map least-frequent)
       (apply str)))

(defn solution-part-two []
  (modified-error-correct messages))

