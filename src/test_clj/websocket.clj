(ns test-clj.websocket
  (:require [jp.nijohando.event :as ev]
            [jp.nijohando.event.websocket :as ws]
            [clojure.core.async :as ca]
            [clojure.data.json :as json]
            [test-clj.averages :as avg]))

(def url "wss://testnet.binancefuture.com/stream?streams=btcusdt@kline_1m")
(def a (/ 2 (inc 5)))

(def bus (ws/client))
(def listener (ca/chan))
(ev/listen bus "/*" listener)

(ca/go-loop [r []]
  (when-some [{:keys [path value]} (ca/<! listener)]
    (println value)
    (prn r)
    (condp = path
      "/message/text"
      (recur
       (let [close (read-string ((((json/read-str value) "data") "k") "c"))]
         (conj r (avg/ema-step a (if (empty? r) close (last r)) close))))
      (recur r))))
(ws/connect! bus url)

(ws/disconnect! bus)


;; {
;;   "e": "kline",     // Event type
;;   "E": 123456789,   // Event time
;;   "s": "BNBBTC",    // Symbol
;;   "k": {
;;     "t": 123400000, // Kline start time
;;     "T": 123460000, // Kline close time
;;     "s": "BNBBTC",  // Symbol
;;     "i": "1m",      // Interval
;;     "f": 100,       // First trade ID
;;     "L": 200,       // Last trade ID
;;     "o": "0.0010",  // Open price
;;     "c": "0.0020",  // Close price
;;     "h": "0.0025",  // High price
;;     "l": "0.0015",  // Low price
;;     "v": "1000",    // Base asset volume
;;     "n": 100,       // Number of trades
;;     "x": false,     // Is this kline closed?
;;     "q": "1.0000",  // Quote asset volume
;;     "V": "500",     // Taker buy base asset volume
;;     "Q": "0.500",   // Taker buy quote asset volume
;;     "B": "123456"   // Ignore
;;   }
;; }

;;"/connect"           (prn "connected!")
;;"/connect-failed"    (prn "connect-failed!")
;;"/disconnect"        (prn "disconnected!")
;;"/disconnect-failed" (prn "disconnect-failed!")
;;"/message/text"      (println (json/read-str value))
;;"/message/binary"    (prn "binary message arrived!")
;;"/message/pong"      (prn "pong message arrived!")
;;"/error"             (prn "error!" value)