import Badge from "../components/Argons/Badge";
import BaseAlert from "../components/Argons/BaseAlert";
import BaseButton from "../components/Argons/BaseButton";
import BaseCheckbox from "../components/Argons/BaseCheckbox";
import BaseInput from "../components/Argons/BaseInput";
import BasePagination from "../components/Argons/BasePagination";
import BaseProgress from "../components/Argons/BaseProgress";
import BaseRadio from "../components/Argons/BaseRadio";
import BaseSlider from "../components/Argons/BaseSlider";
import BaseSwitch from "../components/Argons/BaseSwitch";
import Card from "../components/Argons/Card";
import Icon from "../components/Argons/Icon";

export default {
  install(Vue) {
    Vue.component(Badge.name, Badge);
    Vue.component(BaseAlert.name, BaseAlert);
    Vue.component(BaseButton.name, BaseButton);
    Vue.component(BaseInput.name, BaseInput);
    Vue.component(BaseCheckbox.name, BaseCheckbox);
    Vue.component(BasePagination.name, BasePagination);
    Vue.component(BaseProgress.name, BaseProgress);
    Vue.component(BaseRadio.name, BaseRadio);
    Vue.component(BaseSlider.name, BaseSlider);
    Vue.component(BaseSwitch.name, BaseSwitch);
    Vue.component(Card.name, Card);
    Vue.component(Icon.name, Icon);
  },
};
