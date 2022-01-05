import React, { PureComponent } from "react";
import { Swiper, SwiperSlide } from "swiper/react";
import SwiperCore, { Navigation } from "swiper";
import "swiper/components/navigation/navigation.scss";
import "../../../css/slide.css";
import "swiper/swiper.scss";
import style from "../../../css/study/study_make/study_make.module.css";
import MakeStudyInformation from "./MakeStudyInformation";
import MakeStudyExplanation from "./MakeStudyExplanation";
import MakeStudyTag from "./MakeStudyTag";
SwiperCore.use([Navigation]);

class MakeStudyContentList extends PureComponent {
  constructor(props) {
    super(props);
    this.state = {
      studyName: "",
      maxNumber: "",
      explanation: "",
      tags: [],
      isDisabled: true,
      isNext: true,
      indexNumber: 0,
    };
    this.handleSubmit = this.handleSubmit.bind(this);
    this.handleStudyName = this.handleStudyName.bind(this);
    this.handleNumber = this.handleNumber.bind(this);
    this.handleExplanation = this.handleExplanation.bind(this);
    this.handleTag = this.handleTag.bind(this);
    this.buttonNextClick = this.buttonNextClick.bind(this);
    this.buttonPrevClick = this.buttonPrevClick.bind(this);
  }
  buttonNextClick = () => {
    this.setState({
      indexNumber: this.state.indexNumber + 1,
    });
  };
  buttonPrevClick = () => {
    this.setState({
      indexNumber: this.state.indexNumber - 1,
    });
  };
  handleStudyName = (name) => {
    this.setState({ studyName: name });
  };
  handleNumber = (number) => {
    this.setState({ maxNumber: number });
  };
  handleExplanation = (explan) => {
    this.setState({ explanation: explan });
  };
  handleTag = (tag) => {
    this.setState({ tags: tag });
  };
  handleSubmit = (e) => {
    e.preventDefault();
    console.log(this.state.studyName);
    
  };
  render() {
    return (
      <React.Fragment>
        <div className={style["slide-wrap"]}>
          <form onSubmit={this.handleSubmit}>
            <div className={style["slide-container"]}>
              <Swiper
                slidesPerView={1}
                slidesPerGroup={1}
                mousewheel={false}
                touchRatio={false}
                navigation={{
                  nextEl: ".swiper-button-next",
                  prevEl: ".swiper-button-prev",
                }}
              >
                <SwiperSlide
                  key="studymake_1"
                  className={style.studymake_slide}
                >
                  <div className={style["studymake_slide-wrap"]}>
                    <MakeStudyInformation
                      handleStudyName={this.handleStudyName}
                      handleNumber={this.handleNumber}
                    />
                  </div>
                </SwiperSlide>
                <SwiperSlide
                  key="studymake_2"
                  className={style.studymake_slide}
                >
                  <div className={style["studymake_slide-wrap"]}>
                    <MakeStudyExplanation
                      handleExplanation={this.handleExplanation}
                    />
                  </div>
                </SwiperSlide>
                <SwiperSlide
                  key="studymake_3"
                  className={style.studymake_slide}
                >
                  <div className={style["studymake_slide-wrap"]}>
                    <MakeStudyTag handleTag={this.handleTag} />
                  </div>
                </SwiperSlide>
              </Swiper>
            </div>
            <div className={style.studymake_buttons}>
              <div
                id={style["prevButton"]}
                onClick={this.buttonPrevClick}
                className="swiper-button-prev"
              >
                <h4>이전</h4>
              </div>
              <div
                id={style["nextButton"]}
                onClick={this.buttonNextClick}
                className={
                  this.state.indexNumber === 2
                    ? `${style.disabled} swiper-button-next`
                    : "swiper-button-next"
                }
              >
                <h4>다음</h4>
              </div>
            </div>
            <div className={style["main__slide-submit"]}>
              <input
                className={this.state.indexNumber === 2 ? "" : style.disabled}
                id={style["submitButton"]}
                type="submit"
                value="개설"
              />
            </div>
          </form>
        </div>
      </React.Fragment>
    );
  }
}

export default MakeStudyContentList;
