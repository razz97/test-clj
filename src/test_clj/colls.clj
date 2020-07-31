(ns test-clj.colls)

(defn length [coll]
  (loop [coll coll i 0]
    (if (empty? coll) i (recur (rest coll) (inc i)))))

(defn reverse-string [s] (reduce #(str %2 %1) "" s))
(defn reverse-collection [coll] (reduce conj () coll))
