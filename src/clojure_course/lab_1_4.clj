(ns clojure-course.lab-1-4)

(defn my-map
  [f coll]
  (reduce
    (fn [acc elem] (concat acc (list (f elem))))
    ()
    coll))

(defn my-filter
  [p coll]
  (reduce
    (fn [acc elem] (if (p elem) (concat acc (list elem)) acc))
    ()
    coll))

(defn grow-one-perm
  [perm alphabet]
  (let [right-alphabet (my-filter (fn [letter] (not= letter (first perm))) alphabet)]
    (my-map (fn [letter] (cons letter perm)) right-alphabet)))

(defn grow-perms
  [perms alphabet]
  (reduce
    (fn [l1 l2] (concat l1 l2))
    (my-map (fn [p] (grow-one-perm p alphabet)) perms)))

(defn grow-perms-n-times
  [perms n alphabet]
  (if (= n 0)
    perms
    (grow-perms-n-times (grow-perms perms alphabet) (- n 1) alphabet)))

(defn reverse-and-str-one-perm [l] (apply str (reverse l)))
(defn reverse-and-str-perms [perms] (my-map reverse-and-str-one-perm perms))

(defn perms
  [alphabet n]
  (reverse-and-str-perms
    (grow-perms-n-times (my-map list alphabet) (- n 1) alphabet)))

(println (perms [\a \b \c] 6))