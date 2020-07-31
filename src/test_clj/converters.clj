(ns test-clj.converters)

(defn convert-candle [dict]
  (let [k ((dict "data") "k")]
    {:open       (k "o")
     :close      (k "c")
     :high       (k "h")
     :low        (k "l")
     :open-time  (k "t")
     :close-time (k "T")
     :symbol     (k "s")
     :interval   (k "i")}))

(defn convert-snapshot [dict]
  (let [k (dict "data")]
    {:price  (k "p")
     :symbol (k "s")
     :time   (k "t")}))