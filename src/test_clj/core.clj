(ns test-clj.core)

(defn sma [n nums]
  (map #(% "sma") (rest (reduce
                         (fn [r v] (let [prev (last r)
                                         prev-v (prev "v")
                                         prev-sum (prev "sum")
                                         curr-sum (- (+ v prev-sum) prev-v)]
                                     (conj r {"v" v "sum" curr-sum "sma" (/ curr-sum n)})))
                         [{"v" (nth nums n) "sum" (apply + (take n nums)) "sma" 0}]
                         (subvec nums n)))))


(defn sma1 [n nums]
  (map #(/ % n)
       (reductions (fn [r [old-v new-v]] (+ new-v (- r old-v)))
                   (apply + (take n nums))
                   (map vector nums (drop n nums)))))


(defn ema
  "
   EMA{N}(this tick) = a * price + (1 - a) * EMA{N}(last tick) 
   First EMA = price
   a = 2 / (N + 1)
  "
  [n nums] (let [a (/ 2 (inc n))]
             (reduce
              (fn [r v] (conj r (+ (* a v) (* (- 1 a) (last r)))))
              (subvec nums 0 1)
              (subvec nums 1))))

(defn ema-step  [a prev price] (+ (* a price) (* (- 1 a) prev)))
(def calc-a #(/ 2 (inc %)))

(defn ema-clean
  "
   EMA{N}(this tick) = a * price + (1 - a) * EMA{N}(last tick) 
   First EMA = price
   a = 2 / (N + 1)
  "
  [n nums] (let [a (calc-a n)]
             (reduce
              (fn [r v] (conj r (ema-step a (last r) v)))
              (subvec nums 0 1)
              (subvec nums 1))))




