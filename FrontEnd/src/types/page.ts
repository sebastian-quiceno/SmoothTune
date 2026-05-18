export type Sort = {
    empty: boolean
    sorted: boolean
    unsorted: boolean
}

export type Pageable = {
    offset: number
    pageNumber: number
    pageSize: number
    paged: boolean
    unpaged: boolean
    sort: Sort
}

export type Page<T> = {
    content: T[]

    empty: boolean
    first: boolean
    last: boolean

    number: number
    numberOfElements: number

    pageable: Pageable

    size: number
    sort: Sort

    totalElements: number
    totalPages: number
}