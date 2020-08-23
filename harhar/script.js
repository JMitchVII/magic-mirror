Splitting().forEach(span => {
  span.words.forEach(word => {
    if (word.parentElement.getAttribute('aria-label')) {
        word.setAttribute('aria-hidden', true)
     }
  });
});