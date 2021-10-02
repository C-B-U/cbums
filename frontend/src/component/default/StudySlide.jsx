import { Swiper, SwiperSlide } from "swiper/react";
import React, { PureComponent } from "react";
import SlideBox from "./SlideBox";
import { Link } from "react-router-dom";
import SwiperCore, { Navigation } from "swiper";
import { FaArrowLeft, FaArrowRight } from "react-icons/fa";
import "swiper/components/navigation/navigation.scss";
import "../../css/slide.css";
SwiperCore.use([Navigation]);

class StudySlide extends PureComponent {
  render() {
    return (
      <React.Fragment>
        <Swiper
          className="swiper_container"
          slidesPerView={3.1}
          slidesPerGroup={3.1}
          spaceBetween={24}
          navigation={{
            nextEl: ".swiper-button-next",
            prevEl: ".swiper-button-prev",
          }}
          breakpoints={{
            1280: {
              slidesPerView: 5.5,
              slidesPerGroup: 5.5,
              spaceBetween: 50,
            },
            600: {
              slidesPerView: 4,
              slidesPerGroup: 4,
              spaceBetween: 30,
            },
          }}
        >
          <Link to="/">
            <SwiperSlide className="swiper_slide" key="study_1">
              <SlideBox studyName="CBUMS" Color="#bab843" />
            </SwiperSlide>
          </Link>
          <Link to="/">
            <SwiperSlide className="swiper_slide" key="study_2">
              <SlideBox studyName="Java" Color="#837d75" />
            </SwiperSlide>
          </Link>
          <Link to="/">
            <SwiperSlide className="swiper_slide" key="study_3">
              <SlideBox studyName="파이썬" Color="#f4c853" />
            </SwiperSlide>
          </Link>
          <Link to="/">
            <SwiperSlide className="swiper_slide" key="study_4">
              <SlideBox studyName="자료구조" Color="#ffb4c6" />
            </SwiperSlide>
          </Link>
          <Link to="/">
            <SwiperSlide className="swiper_slide" key="study_5">
              <SlideBox studyName="C언어" Color="#54d6b6" />
            </SwiperSlide>
          </Link>
          <Link to="/">
            <SwiperSlide className="swiper_slide" key="study_6">
              <SlideBox studyName="C++" Color="#c3a2f3" />
            </SwiperSlide>
          </Link>
        </Swiper>
        <div className="swiper-button-prev button">
          <FaArrowLeft />
        </div>
        <div className="swiper-button-next button">
          <FaArrowRight />
        </div>
      </React.Fragment>
    );
  }
}

export default StudySlide;
