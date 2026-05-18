import {ChevronLeft, ChevronRight} from 'lucide-react'

type ChangePageProps ={
	onClickPrevious: ()=> void
	onClickNext: ()=> void
	pageNumber: number
	maxPage: number	
}

export const ChangePage = ({onClickPrevious, onClickNext, pageNumber, maxPage}:ChangePageProps) => {
  return (
    <div className='flex flex-row justify-center items-center mt-4'>
      <ChevronLeft onClick={pageNumber > 0 ? onClickPrevious : undefined} className={`w-5 h-5 ${pageNumber > 0 ?"text-white":"text-white/60"} cursor-pointer`}/>
			<span className='text-2xl'>{pageNumber + 1}</span>
			<ChevronRight onClick={pageNumber < maxPage-1 ? onClickNext : undefined} className={`w-5 h-5 ${pageNumber < maxPage-1 ?"text-white":"text-white/60"} cursor-pointer`}/>
    </div>
  );
};
