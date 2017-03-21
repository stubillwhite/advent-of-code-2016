(ns advent-of-code-2016.core
  (:require
    [taoensso.timbre :as timbre]))

(timbre/refer-timbre)

(defn add-one
  ([x] 
   (+ x 1)))
