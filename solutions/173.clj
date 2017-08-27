;;; solution for 173. Intro into destruction 2

;; Sequential destructuring allows you to bind symbols to parts of sequential things (vectors, lists, seqs, etc.): (let [bindings* ] exprs*) Complete the bindings so all let-parts evaluate to 3.


;; __ = z t

(= 3

  (let [[z t] [+ (range 3)]] (apply z t))

  (let [[[z t] b] [[+ 1] 2]] (z t b))

  (let [[z t] [inc 2]] (z t)))
