(ns advent-of-code-2016.day-1
  (:require [advent-of-code-2016.utils :refer [def-]]
            [clojure.string :as string]))

(def directions-to-hq
  (let [raw-input "L5, R1, R4, L5, L4, R3, R1, L1, R4, R5, L1, L3, R4, L2, L4, R2, L4, L1, R3, R1, R1, L1, R1, L5, R5, R2, L5, R2, R1, L2, L4, L4, R191, R2, R5, R1, L1, L2, R5, L2, L3, R4, L1, L1, R1, R50, L1, R1, R76, R5, R4, R2, L5, L3, L5, R2, R1, L1, R2, L3, R4, R2, L1, L1, R4, L1, L1, R185, R1, L5, L4, L5, L3, R2, R3, R1, L5, R1, L3, L2, L2, R5, L1, L1, L3, R1, R4, L2, L1, L1, L3, L4, R5, L2, R3, R5, R1, L4, R5, L3, R3, R3, R1, R1, R5, R2, L2, R5, L5, L4, R4, R3, R5, R1, L3, R1, L2, L2, R3, R4, L1, R4, L1, R4, R3, L1, L4, L1, L5, L2, R2, L1, R1, L5, L3, R4, L1, R5, L5, L5, L1, L3, R1, R5, L2, L4, L5, L1, L1, L2, R5, R5, L4, R3, L2, L1, L3, L4, L5, L5, L2, R4, R3, L5, R4, R2, R1, L5"]
    (string/split raw-input #", ")))

(def- initial-state
  {:x      0
   :y      0
   :facing :north})

(defn- rotate [{:keys [facing]} dir]
  (get-in {\L {:north :west
               :east  :north
               :south :east
               :west  :south}
           \R {:north :east
               :east  :south
               :south :west
               :west  :north}}
          [dir facing]))

(defn- coordinate-multiplier [dir]
  (get {:north {:x 0  :y 1}
        :east  {:x 1  :y 0}
        :south {:x 0  :y -1}
        :west  {:x -1 :y 0}}
       dir))

(defn- follow-direction [state direction]
  (let [dir        (first (seq direction))
        dist       (Long/valueOf (string/join (rest (seq direction))))
        facing     (rotate state dir)
        multiplier (coordinate-multiplier facing)]
    (-> state
        (update :x (fn [x] (+ x (* dist (:x multiplier)))))
        (update :y (fn [y] (+ y (* dist (:y multiplier)))))
        (assoc :facing facing))))

(defn- cityblock-distance [{:keys [x y]}]
  (+ (Math/abs x) (Math/abs y)))

(defn- relative-coordinates [directions]
  (reduce follow-direction initial-state directions))

(defn distance-to-destination [directions]
  (cityblock-distance (relative-coordinates directions)))

(defn solution-part-one []
  (cityblock-distance (relative-coordinates directions-to-hq)))

