
;;; Solution for  121. Universal Computation


;; Given a mathematical formula in prefix notation, return a function that calculates the value of the formula. The formula can contain nested calculations using the four basic mathematical operators, numeric constants, and symbols representing variables. The returned function has to accept a single parameter containing the map of variable names to their values.

;;  Special Restrictions: eval resolve


(defn compute
   [input]
    (fn doit
       ([args] (doit input (conj args ['+ +] ['/ /] ['* *] ['- -]))); don't know how to apply an operator otherwise
       ([command args]
        (cond
           (sequential? command) (let [operator  (args (first command))
                                       arguments (for [arg (rest command)] (doit arg args))]
                                       (apply operator arguments)
                                 )
           (symbol? command) (args command)
           :else  command;for example a number
        )
      )
     )
)

(def __ compute)




; tests
(= 2 ((__ '(/ a b))
      '{b 8 a 16}))

(= 8 ((__ '(+ a b 2))
      '{a 2 b 4}))

(= [6 0 -4]
     (map (__ '(* (+ 2 a)
                  (- 10 b)))
            '[{a 1 b 8}
              {b 5 a -2}
              {a 2 b 11}]))


(= 1 ((__ '(/ (+ x 2)
              (* 3 (+ y 1))))
      '{x 4 y 1}))

