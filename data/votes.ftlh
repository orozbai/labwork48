<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Who's winning?</title>
    <link rel="stylesheet" href="candidates.css">
</head>

<body>
<div class="container flex flex-col align-center">
    <h1>Who's winning?</h1>
    <main class="flex flex-wrap align-center">
        <#assign maxPercentage = 0.0>
        <#list candidates as candidate>
            <#if candidate.percentage gt maxPercentage>
                <#assign maxPercentage = candidate.percentage>
            </#if>
        </#list>
        <#list candidates as candidate>
            <#if candidate.percentage == maxPercentage>
                <div class="card winner">
                    <div class="flex flex-col align-center" href="#">
                        <img src="${candidate.photo}" alt="${candidate.name}">
                        <p>Votes: ${candidate.percentage}%</p>
                    </div>
                </div>
            <#else>
                <div class="card">
                    <div class="flex flex-col align-center" href="#">
                        <img src="${candidate.photo}" alt="${candidate.name}">
                        <p>Votes: ${candidate.percentage}%</p>
                    </div>
                </div>
            </#if>
        </#list>
        <a class="back flex align-center" href="/">back to main</a>
    </main>
</div>
</body>

</html>