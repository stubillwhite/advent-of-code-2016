(ns advent-of-code-2016.day-1
  (:require [clojure.java.io :as io]
            [clojure.string :as string]))

(def steps-to-hq
  (-> (io/resource "day-1-input.txt")
      slurp
      (string/trim)
      (string/split #", ")))

(defn create-state [steps]
  {:x          0
   :y          0
   :facing     :north
   :visited    #{}
   :steps      steps
   :curr-step  nil})

(defn- rotate [facing dir]
  (get-in {\L {:north :west
               :east  :north
               :south :east
               :west  :south}
           \R {:north :east
               :east  :south
               :south :west
               :west  :north}
           \S {:north :north
               :east  :east
               :south :south
               :west  :west}}
          [dir facing]))

(defn- coordinate-delta [dir]
  (get {:north [  0  1 ]
        :east  [  1  0 ]
        :south [  0 -1 ]
        :west  [ -1  0 ]}
       dir))

(defn- decode-step [step]
  [(first (seq step))
   (Long/valueOf (string/join (rest (seq step))))])

(defn update-steps [steps]
  (let [[dir dist] (decode-step (first steps))]
    (if (= 1 dist)
      (rest steps)
      (cons (str "S" (dec dist)) (rest steps)))))

(defn- update-state [{:keys [x y facing visited steps curr-step]}]
  (let [step       (first steps)
        [dir dist] (decode-step step)
        new-facing (rotate facing dir)
        [dx dy]    (coordinate-delta new-facing)]
    {:x         (+ x dx)
     :y         (+ y dy)
     :facing    new-facing
     :visited   (conj visited [x y])
     :steps     (update-steps steps)}))

(defn- cityblock-distance [{:keys [x y]}]
  (+ (Math/abs x) (Math/abs y)))

(defn- follow-steps [state arrived?]
  (loop [state state]
    (if (arrived? state)
      state
      (recur (update-state state)))))

(defn distance-to-destination [state arrived?]
  (cityblock-distance (follow-steps state arrived?)))

(defn all-steps-completed? [{:keys [steps]}]
  (not (seq steps)))

(defn solution-part-one []
  (distance-to-destination (create-state steps-to-hq) all-steps-completed?))

(defn visited-twice? [{:keys [x y visited]}]
  (contains? visited [x y]))

(defn solution-part-two []
  (distance-to-destination (create-state steps-to-hq) visited-twice?))
