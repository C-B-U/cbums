import { Swiper, SwiperSlide } from "swiper/react";
import React, { PureComponent } from "react";
import "swiper/css";

import SlideBox from "./SlideBox";
import { Link } from "react-router-dom";
//import SwiperCore, { Navigation } from "swiper";
//SwiperCore.use([Navigation]);
class Slide extends PureComponent {
  render() {
    return (
      <React.Fragment>
        <Swiper
          slidesPerView={3.1}
          slidesPerGroup={3.1}
          spaceBetween={24}
          breakpoints={{
            1280: {
              slidesPerView: 5.5,
              slidesPerGroup: 5.5,
            },
            600: {
              slidesPerView: 4,
              slidesPerGroup: 4,
            },
          }}
        >
          <Link to="/">
            <SwiperSlide>
              <SlideBox studyNam="CBUMS" Color="#bab843" />
            </SwiperSlide>
          </Link>
          <Link to="/">
            <SwiperSlide>
              <SlideBox studyNam="Java" Color="#837d75" />
            </SwiperSlide>
          </Link>
          <Link to="/">
            <SwiperSlide>
              <SlideBox studyNam="파이썬" Color="#f4c853" />
            </SwiperSlide>
          </Link>
          <Link to="/">
            <SwiperSlide>
              <SlideBox studyNam="자료구조" Color="#ffb4c6" />
            </SwiperSlide>
          </Link>
          <Link to="/">
            <SwiperSlide>
              <SlideBox studyNam="C언어" Color="#54d6b6" />
            </SwiperSlide>
          </Link>
          <Link to="/">
            <SwiperSlide>
              <SlideBox studyNam="C++" Color="#c3a2f3" />
            </SwiperSlide>
          </Link>
        </Swiper>
      </React.Fragment>
    );
  }
}

export default Slide;
