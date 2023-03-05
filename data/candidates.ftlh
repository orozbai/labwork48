<!DOCTYPE html>
<html lang="en">
<head><style>
body {
    margin: 0;
    padding: 0;
    font-size: 16px;
    font-family: Arial, "Helvetica Neue", Helvetica, sans-serif;
}

body {
    background: gainsboro;
}

a {
    text-decoration: none;
    color: black;
}

img {
    border-radius: 5px;
}



.back {
    width: 280px;
    padding: 10px 0px;
    background-color: black;
    color:orange;
    border-radius: 5px;
}
.back:hover {
    transition-duration: 0.3s;
    background-color: orange;
    color:black;
}

.vote {
    border: 0;
    width: 280px;
    text-transform: uppercase;
    padding: 5px 0;
    background-color: gray;
    color: black;
    font-size: 16px;
    margin: 3px 0 0 0;
    border-radius: 5px;
}

.vote:hover {
    background-color: orange;
}

.container {
    width: 1000px;
    margin: 0 auto;
}

.flex {
    display: flex;
}

.flex-col {
    flex-direction: column;
}

.flex-wrap {
    flex-wrap: wrap;
}

.align-evenly {
    justify-content: space-around;
}

.align-center {
    align-items: center;
    justify-content: center;
}

.card {
    flex: 300px 0 0;
    box-sizing: border-box;
    margin: 20px 0 0 0;
    padding: 10px 0;
    border-radius: 5px;
}

.card:hover {
    background-color: whitesmoke;
}

.winner {
    background-color: crimson;
}</style>
    <meta charset="UTF-8">
    <title>Vote for us!</title>
    <link rel="stylesheet" href="css/candidates.css">
</head>
<body>
    <div class="container flex flex-col align-center">
    <h1>Vote for us</h1>
    <main class="flex flex-wrap align-evenly">
        <#list candidates as candidate>
    <div class="card">
      <form action="/vote" method="post" class="flex flex-col align-center">
          <img src="${candidate.photo}" alt="${candidate.name}">
        <p>${candidate.name}</p>
        <input name="candidateId" type="hidden" value="${candidate.name}">
        <button type="submit" class="vote flex align-center">Vote</button>
      </form>
    </div>
        </#list>
    </main>
      <p>-------------------------------------------------------</p>
      <a class="back flex align-center" href="/votes">Show total votes</a>
    </div>
</body>

</html>
