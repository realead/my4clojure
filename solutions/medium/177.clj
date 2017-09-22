;;; Solution for 177. Balancing Brackets


;;When parsing a snippet of code it's often a good idea to do a sanity check to see if all the brackets match up. Write a function that takes in a string and returns truthy if all square [ ] round ( ) and curly { } brackets are properly paired and legally nested, or returns falsey otherwise.


(def brackets-look-up {\] \[, \) \( \} \{})
(def all-brackets (set (flatten (seq brackets-look-up))))

(defn brackets-ok?
  [text]
  (loop [ stack ()
         text text]
         (if (empty? text)
             (empty?  stack)
             (let [b (first text)
                   is-not-bracket (not (contains? all-brackets b))
                   is-closing (contains? brackets-look-up b)]
                   (cond
                       is-not-bracket  (recur stack (rest text))
                       is-closing      (if (=  (peek stack) (brackets-look-up b))
                                           (recur (pop stack) (rest text))
                                           false)
                       :else (recur (conj stack b) (rest text))
                   )
             )
         )
  )
)


(def __ brackets-ok?)


;tests

(__ "This string has no brackets.")
(__ "class Test {
      public static void main(String[] args) {
        System.out.println(\"Hello world.\");
      }
    }")

(not (__ "(start, end]"))
(not (__ "())"))
(not (__ "[ { ] } "))
(__ "([]([(()){()}(()(()))(([[]]({}()))())]((((()()))))))")
(not (__ "([]([(()){()}(()(()))(([[]]({}([)))())]((((()()))))))"))
(not (__ "["))
