import React, { PureComponent } from "react";
import style from "../../../css/study/study_make/study_make.module.css";

class MakeStudyInformation extends PureComponent {
  constructor(props) {
    super(props);
    this.state = {
      studyName: '',
      maxNumber: '',
    };
    this.handleStudyName = this.handleStudyName.bind(this);
    this.handleNumber = this.handleNumber.bind(this);
  }
  handleStudyName = (e) => {
    this.setState({
      studyName: e.target.value,
    });
    this.props.handleStudyName(this.state.studyName);
  }
  handleNumber = (e) => {
    this.setState({
      maxNumber: e.target.value,
    });
    this.props.handleNumber(this.state.maxNumber);
  }
  render() {
    return (
      <React.Fragment>
        <div className={style["main__studymake-information"]}>
          <div className={style["main__slide-studyname"]}>
            스터디명
            <input
              className={style.studymake_input}
              type="text"
              value={this.state.studyName}
              name="studyname"
              autoComplete="off"
              onChange={this.handleStudyName}
            />
          </div>
          <div className={style["main__slide-duration"]}>
            진행 기간
            <input
              className={style.studymake_input}
              type="date"
              name="startDate"
            />
            ~
            <input
              className={style.studymake_input}
              type="date"
              name="endDate"
            />
          </div>
          <div className={style["main__slide-number"]}>
            모집 인원
            <input
              className={style.studymake_input}
              type="text"
              value={this.state.maxNumber}
              name="maxNumber"
              autoComplete="off"
              onChange={this.handleNumber}
            />
            명
          </div>
        </div>
      </React.Fragment>
    );
  }
}

export default MakeStudyInformation;
