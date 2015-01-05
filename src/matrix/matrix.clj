(ns matrix.matrix
  (:require [clojure.pprint :refer (pprint)]))


(defn vov
  [h w]
  (vec (repeat h (vec (repeat w 0)))))

(defprotocol Matrix
  (lookup [this i j])
  (update [this i j valud])
  (rows [this])
  (cols [this])
  (dims [this])
  (show [this]))

(extend-protocol Matrix
  clojure.lang.IPersistentVector
  (lookup [vov i j]
    (get-in vov [i j]))
  (update [vov i j value]
    (assoc-in vov [i j] value))
  (rows [vov]
    (seq [vov]))
  (cols [vov]
    (apply map vector vov))
  (dims [vov]
    [(count vov) (count (first vov))])
  (show [vov]
    (reduce (fn [s v]
              (str
               (reduce
                #(str %1 %2 " ")
                s v)
               "\n"))
            "" (cols vov))))

;;todo 矩阵的加减乘除，方阵的行列式
