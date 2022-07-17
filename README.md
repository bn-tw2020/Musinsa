## musinsa

### 사용 기술

- Retrofit2, Moshi, Hilt, Kotlin Flow
- Coroutine, RecyclerView, MVVM

### 프로젝트 구조

```bash
├── data
│   ├── datasource
│   │    ├── AssetLoader.kt
│   │    ├── HomeAssetDataSource.kt
│   │    ├── HomeDataSource.kt
│   │    └── HomeRemoteDataSource.kt
│   │
│   ├── model
│   │    ├── Banner.kt
│   │    ├── Contents.kt
│   │    ├── Data.kt
│   │    ├── Footer.kt
│   │    ├── Good.kt
│   │    ├── Header.kt
│   │    ├── ListResponse.kt
│   │    └── Style.kt
│   │
│   ├── network
│   │    ├── MusinsaService.kt
│   │    └── URL.kt
│   │
│   └── repository
│        └── HomeRepositoryImpl.kt
│   
├── di
│   ├── Annotation.kt
│   ├── DataSourceModule.kt
│   ├── NetworkModule.kt
│   └── RepositoryModule.kt
│
├── domain
│   └── repository
│        └── HomeRepository.kt
│
├── presentation
│   ├── adapter
│   │    ├── banner
│   │    │    ├── BannerAdapter.kt
│   │    │    └── BannerSectionAdapter.kt
│   │    │
│   │    ├── scroll_grid
│   │    │    ├── GoodAdapter.kt
│   │    │    ├── GridSectionAdapter.kt
│   │    │    └── ScrollSectionAdapter.kt
│   │    │
│   │    ├── style
│   │    │    ├── StyleAdapter.kt
│   │    │    └── StyleSectionAdapter.kt
│   │    └── DataDiff.kt
│   │    
│   ├── common
│   │    ├── ImageViewBindingAdapter.kt
│   │    └── TextViewBindingAdapter.kt
│   ├── HomeViewModel.kt
│   ├── MainActivity.kt
│   ├── Type.kt
│   └── UiState.kt
│   
│── util
│   └── GlideModule.kt
│
└── MusinsaApplication.kt
``` 
