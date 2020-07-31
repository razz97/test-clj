(ns test-clj.core
  (:require [clojure.string :as Str]
            [test-clj.converters :as cnv]
            [clj-time.core :as time]
            [clj-time.coerce :as tc]))

(defn armstrong? [n]
  (loop [acc 0 digits (map read-string (Str/split (str n) #""))]
    (if (empty? digits)
      (= n (int acc))
      (recur (+ acc (Math/pow (first digits) 3)) (rest digits)))))

(defn armstrong2? [n]
  (= (double n)
     (apply + (map #(Math/pow (Integer/parseInt %) 3) (Str/split (str n) #"")))))

(defn armstrong-numbers
  [from to] (filter
             (fn [n] (= (double n)
                        (apply + (map #(Math/pow (Integer/parseInt %) 3)
                                      (Str/split (str n) #"")))))
             (range from to)))

(def epoch (tc/to-long (time/now)))

(:time (cnv/convert-snapshot {"data" {"p" 100 "s" "BTCUSD" "t" epoch}}))
