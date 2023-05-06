/**
 * 
 */
let labels=document.querySelectorAll('.tbReponse tr td label');
	labels.forEach((lab,ix)=>{
		
		
		lab.addEventListener("click",()=>{
			
			lab.parentNode.classList.add('bg-blue-500')
			
			labels.forEach((lb,i)=>{
				if(ix!=i && lab.parentNode.classList.contains('bg-blue-500')){
				lb.parentNode.classList.remove('bg-blue-500')
			}
			})
		})
		
		
	})