;;
;; Hooks
;;

;; this is necessary as long as leiningen.javac plugin does not hook into
;; test tasks as well as clean and compile

(ns leiningen.hooks.javac
  (:require leiningen.test)
  (:use [clojure.contrib.io :only [file delete-file delete-file-recursively]]
        [leiningen.javac :only (javac extract-javac-tasks)]
        robert.hooke))

(defn java-compilation-hook [task & args]
  (println "About to compile Java sources")
  (apply javac args)
  (apply task args))

(add-hook #'leiningen.test/test java-compilation-hook)
(add-hook #'leiningen.repl/repl java-compilation-hook)