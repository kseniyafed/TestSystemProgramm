<!DOCTYPE html>
<html>
    <head>
        <title>TestSystem</title>
        <meta charset="UTF-8">
        <style>
            .block1 { 
                width:95%;
                padding: 10px;  
                font-size: 80%; 
                text-align: right
            }
            .block2{    
                width: 200%;
                height: 50px;
                padding: 5px;
                position:relative;
                left:5%;
                top:25px;
                color: #191970; 
                font-size: 150%;
            }
        </style>
 </head>
    <body>
        <body bgcolor="#ADD8E6">
        <div class="block1">
            <a href="/">${login} Выход</a>
        </div>  
        <h1 align="center" style="font-size: 20px"><font color="#191970">Редактор тестов</font></h1>
        <h1 align="center"><font color="#191970">${subject.name}</font></h1>
        <form action="/testEditor/${subject.idSubject}" method="POST">
         <#list questions as question>
               <h1 align="left" style="font-size: 20px"><font color="#191970">Номер вопроса: ${question.number}</font></h1>
                <div class="block2">
                
                <label for="${question.idQuestion}question">Вопрос: &nbsp</label>
                <input type="text" class="form-control" id="${question.idQuestion}question" name="${question.idQuestion}question" contenteditable="true"  style="font-size: 20px" value="${question.formulation}"></input>
                <label for="${question.idQuestion}answer">Ответ:</label> 
                <input type="text" class="form-control" id="${question.idQuestion}answer" name="${question.idQuestion}answer" contenteditable="true"  style="font-size: 20px" value="${question.answer}"></input>
        
            </div>
            
            
 
         </#list>
         <p style="text-align: center">      
               
                <button type="submit" >Сохранить</button>
            </p> 
            
              </div> 
     </form>
     <form action="/teacherPage" method="POST">
                <p style="text-align: center">      
                    <button type="submit" >На главную</button>
                </p>  
            </form>
</body>
    </html>