(ns advent-of-code-2016.day-9
  (:require [advent-of-code-2016.utils :refer [def-]]
            [clojure.string :as string]
            [clojure.java.io :as io]))

(def- marker-regex #"^\((\d+)x(\d+)\)")

(defn decode-marker [s]
  (if-let [[match chars reps] (re-find marker-regex s)]
    {:stream (subs s (.length match))
     :chars  (Long/valueOf chars)
     :reps   (Long/valueOf reps)}))

(defn- decompress-chunk [s]
  (if-let [{:keys [stream chars reps]} (decode-marker s)]
    {:chunk  (apply str (repeat reps (subs stream 0 chars)))
     :stream (subs stream chars)}
    {:chunk  (subs s 0 1)
     :stream (subs s 1)}))

(defn- decompress-iter [acc s]
  (if (empty? s)
    acc
    (let [{:keys [chunk stream]} (decompress-chunk s)]
      (recur (str acc chunk) stream))))

(defn decompress [s]
  (decompress-iter "" s))

(defn decompressed-length [s]
  (count (decompress s)))

(def- compressed-file
  (string/trim (slurp (io/resource "day-9-input.txt"))))

(defn solution-part-one []
  (decompressed-length compressed-file))

