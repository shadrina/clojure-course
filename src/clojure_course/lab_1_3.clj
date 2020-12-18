(ns clojure-course.lab-1-3)

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

(println (my-map (fn [x] (* x 3)) (range 5)))
(println (my-map inc [1 2 3 4 5]))
(println (my-filter (fn [x] (= (mod x 2) 0)) (range 6)))
(println (my-filter even? (range 10)))
