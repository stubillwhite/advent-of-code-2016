(ns advent-of-code-2016.day-5
  (:require [advent-of-code-2016.utils :refer [def-]]
            [clojure.string :as string])
  (:import java.security.MessageDigest
           javax.xml.bind.annotation.adapters.HexBinaryAdapter))

(defn- salted-hashes [seed]
  (let [digester   (MessageDigest/getInstance "MD5")
        to-hex-str (fn [x] (.marshal (HexBinaryAdapter.) x))]
    (for [n (range)]
      (to-hex-str
       (.digest digester (.getBytes (str seed n)))))))

(defn- interesting? [s]
  (string/starts-with? s "00000"))

(defn- nth-char [n s]
  (nth (seq s) n))

(defn- interesting-hashes [id]
  (filter interesting? (salted-hashes id)))

(defn simple-password [id]
  (->> (interesting-hashes id)
       (take 8)
       (map (partial nth-char 5))
       (apply str)))

(defn solution-part-one []
  (simple-password "abbhdwsy"))

(defn- to-int [ch]
  (Integer/parseInt (str ch)))

(defn- valid-pos? [ch]
  (and (contains? #{\1 \2 \3 \4 \5 \6 \7 \8 \9 \0} ch)
       (< (to-int ch) 8)))

(defn- complete? [s]
  (not (some nil? s)))

(defn- set-char [password hash]
  (let [pos (nth hash 5)
        ch  (nth hash 6)]
    (if (and (valid-pos? pos) (nil? (nth password (to-int pos))))
      (assoc password (to-int pos) ch)
      password)))

(defn complex-password [id]
  (apply str
         (loop [password (vec (repeat 8 nil))
                hashes   (interesting-hashes id)]
           (if (complete? password)
             password
             (recur
              (set-char password (first hashes))
              (rest hashes))))))



