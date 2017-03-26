(ns advent-of-code-2016.day-4
  (:require [clojure.java.io :as io]
            [clojure.string :as string]))

(def raw-input
  (string/split (slurp (io/resource "day-4-input.txt")) #"\n"))

(defn decode-room-id [s]
  (let [matches (re-matches #"(.+)-(.+)\[(.+)\]" s)
        [all name security-id checksum] matches]
    {:name        name
     :security-id (Long/parseLong security-id)
     :checksum    checksum}))

(defn- cmp-count-then-alpha [[ch1 n1] [ch2 n2]]
  (compare [n2 ch1] [n1 ch2]))

(defn- letter-counts [s]
  (let [letters (filter #(not= % \-) (seq s))]
    (reduce
     (fn [acc x] (update acc x (fnil inc 0)))
     {}
     letters)))

(defn- ordered-letter-counts [s]
  (into (sorted-set-by cmp-count-then-alpha)
        (letter-counts s)))

(defn- calculate-checksum [s]
  (let [counts (ordered-letter-counts s)]
    (string/join (for [[ch _] (take 5 counts)] ch))))

(defn real? [{:keys [name checksum]}]
  (= (calculate-checksum name) checksum))

(defn sum-of-sector-ids [rooms]
  (reduce + (->> rooms
                 (map decode-room-id)
                 (filter real?)
                 (map :security-id))))

(defn solution-part-one []
  (sum-of-sector-ids raw-input))

