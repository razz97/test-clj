(ns test-clj.averages)

(defn sma
  "Returns a vector mapping the simple moving average of previous n elements for every 
   element of nums except the first n that are just used as the starting point"
  [n nums]
  (map #(/ % n)
       (reductions
        (fn [r [old-v new-v]] (+ new-v (- r old-v)))
        (apply + (take n nums))
        (map vector nums (drop n nums)))))

(defn ema
  "Returns a vector mapping the exponential moving average of all elements of nums, n is the ratio"
  [n nums]
  (let [a (/ 2 (inc n))]
    (reduce
     (fn [r v] (conj r (+ (* a v) (* (- 1 a) (last r)))))
     (subvec nums 0 1)
     (subvec nums 1))))

(defn ema-step  [a prev price] (+ (* a price) (* (- 1 a) prev)))
(def calc-a #(/ 2 (inc %)))