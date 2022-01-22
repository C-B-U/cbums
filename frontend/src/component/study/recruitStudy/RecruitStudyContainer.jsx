import React, { PureComponent } from "react";
import axios from "axios";
import RecruitStudyItemList from "./RecruitStudyItemList";
class RecruitStudyContainer extends PureComponent {
  constructor(props) {
    super(props);
    this.state = {
      ItemList: [],
      loading: false,
    };
    this.getStudy = this.getStudy.bind(this);
  }
  getStudy = () => {
    axios.get("/api/project")
      .then(res => {
        this.setState({
          loading: true,
          ItemList: res.data,
        });
      })
      .catch((e) => {
        console.log(e);
        this.setState({
          loading:false,
        })
      });
  }
  componentDidMount() {
    this.getStudy();
  }
    render() {
        return (
          <React.Fragment>
            <RecruitStudyItemList ItemList={this.state.ItemList}/>
          </React.Fragment>
        );
    }
}

export default RecruitStudyContainer;
