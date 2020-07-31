;; (ns test-clj.cache)

;; (def state (atom  {}))
;; (swap! state assoc :a 1)

;; {:a 1} -> (hash-map :a 1)

;; (source if)

;; (defn get-data
;;   ([ticker default] (if-let [cached (@state ticker)] cached default))
;;   ([ticker] (get-data ticker nil)))

;; (defn set-data [dct ticker] (swap! @state (assoc @state ticker dct)))


;; ;; (defn cached [ticker form]
;; ;;   (let [state (atom {})]
;; ;;     (fn [] (if-let [cached (do (println @state) (@state ticker))]
;; ;;              cached
;; ;;              (let [res (eval form)] (swap! state (assoc @state ticker res)) res)))))

;; ;; (def calculate '(do (Thread/sleep 2000) {:sma [13.1 15.2 17.3 15.5 16.1]}))
;; ;; (time (cached "BTCUSD" calculate))
;; ;; (time (cached "BTCUSD" calculate))

;; ;; (def foo
;; ;;   (let [counter (atom 0)]
;; ;;     (fn [] (do (swap! counter inc) @counter))))


;; (defmacro suma-to-resta [form]
;;   (let [op (first form)]
;;     (case op
;;       + (cons - (rest form))
;;       - (cons + (rest form)))))

;; (macroexpand '(suma-to-resta (+ 1 2)))

;; (defn memo [f]
;;   (let [cache (atom {})]
;;     (fn [& args]
;;       (if-let [cached (@cache args)]
;;         (val cached)
;;         ((swap! cache assoc args (apply f args)) args)))))

;; ;; (def my-func (memo (fn [a b] (do (Thread/sleep 2000) (+ a b)))))
;; ;; (my-func 1 2)

;; ;; (time (my-func 10 20))


(defn my-reduce [f ide coll]
  (let [acc (atom ide)]
    (doall (map (fn [elem] (swap! acc f elem)) coll))
    @acc))










