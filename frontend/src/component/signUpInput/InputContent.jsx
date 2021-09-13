import React, { PureComponent } from "react";
import { FaArrowLeft, FaArrowRight } from "react-icons/fa";
import { Swiper, SwiperSlide } from "swiper/react";
import SwiperCore, { Navigation } from "swiper";
import "swiper/components/navigation/navigation.scss";
import "../../css/slide.css";
SwiperCore.use([Navigation]);

class InputContent extends PureComponent {
  render() {
    return (
      <React.Fragment>
        <div className="article__boxList">
          <Swiper
            className="input-container"
            slidesPerView={1}
            slidesPerGroup={1}
            mousewheel={false}
            touchRatio={false}
            navigation={{
              nextEl: ".swiper-button-next",
              prevEl: ".swiper-button-prev",
            }}
          >
            <form action="#" method="POST">
              <ul className="boxList__infor">
                <li>
                  <SwiperSlide className="box" key="input_1">
                    <label>이름</label>
                    <input
                      type="text"
                      required
                      placeholder="이름 입력"
                      name="name"
                    />
                  </SwiperSlide>
                </li>
                <SwiperSlide className="box" key="input_2">
                  <label>학과</label>
                  <input
                    type="text"
                    placeholder="학번 입력"
                    name="schoolNumber"
                    required
                  />
                </SwiperSlide>
                <SwiperSlide className="box" key="input_3">
                  <label>전화번호</label>
                  <input
                    type="text"
                    placeholder="전화번호 입력"
                    name="phoneNumber"
                    required
                  />
                </SwiperSlide>
                <SwiperSlide className="box" key="input_4">
                  <input
                    type="submit"
                    style={{ cursor: "pointer" }}
                    className="boxList__infor--submit"
                  />
                </SwiperSlide>
              </ul>
            </form>
          </Swiper>
          <div className="swiper-button-prev button">
            <FaArrowLeft />
          </div>
          <div className="swiper-button-next button">
            <FaArrowRight />
          </div>
        </div>
      </React.Fragment>
    );
  }
}

export default InputContent;
/*
 
 */
