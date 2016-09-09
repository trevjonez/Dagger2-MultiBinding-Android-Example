# Dagger 2 sub-component map binding example

This attempts to add sub component builders to an application via the `@Binds @IntoMap @ActivityKey(...)` method shown [here.](https://youtu.be/iwjXqRlEevg)

THis however does not quite work. It is still necessary to have your sub-component builder manually exposed on the parent component. See [AppComponent](https://github.com/trevjonez/Dagger2-MultiBinding-Android-Example/app/src/main/java/com/trevjonez/daggertest/application/AppComponent.java) for clarification of what I mean by this.