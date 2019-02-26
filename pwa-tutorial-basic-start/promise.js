var promise1 = new Promise(function(resolve, reject) {
  setTimeout(function() {
    resolve('fooo');
  }, 3000);
});

promise1.then(function(value) {
  console.log(value);
  // expected output: "foo"
});

console.log(promise1);
// expected output: [object Promise]